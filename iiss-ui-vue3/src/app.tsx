/* *
 *
 * @author whiteshader@163.com
 * @datetime  2022/02/15
 *
 * */

import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import { PageLoading, SettingDrawer } from '@ant-design/pro-layout';
import type { RunTimeLayoutConfig } from 'umi';
import { history, Link } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import { BookOutlined, LinkOutlined } from '@ant-design/icons';
import defaultSettings from '../config/defaultSettings';
import { getUserInfo, getRoutersInfo } from './services/session';

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';
const socialLogin = '/social-login';

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  currentUser?: API.CurrentUser;
  loading?: boolean;
  fetchUserInfo?: () => Promise<API.CurrentUser | undefined>;
}> {
  const fetchUserInfoLogin = async () => {
    try {
      const resp = await getUserInfo();
      if (resp === undefined || resp.code !== 200) {
      } else {
        return { ...resp.user, permissions: resp.permissions } as API.CurrentUser;
      }
    } catch (error) {
    }
    return undefined;
  };
  const fetchUserInfo = async () => {
    try {
      const resp = await getUserInfo();
      if (resp === undefined || resp.code !== 200) {
        history.push(loginPath);
      } else {
        return { ...resp.user, permissions: resp.permissions } as API.CurrentUser;
      }
    } catch (error) {
        history.push(loginPath);
    }
    return undefined;
  };
  if (history.location.pathname === socialLogin) {
    return {
      fetchUserInfo:fetchUserInfoLogin,
      settings: defaultSettings,
    };
  }
  // 如果是登录页面，不执行
  if (history.location.pathname !== loginPath) {
    const currentUser = await fetchUserInfo();
    console.info('获得用户信息');
    console.info(currentUser);
    return {
      settings: defaultSettings,
      currentUser,
      fetchUserInfo,
    };
  }
  return {
    fetchUserInfo,
    settings: defaultSettings,
  };
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
export const layout: RunTimeLayoutConfig = ({ initialState, setInitialState }) => {
  return {
    rightContentRender: () => <RightContent />,
    waterMarkProps: {
      content: initialState?.currentUser?.userName,
    },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      if (location.pathname === socialLogin) {
      } else {
        // 如果没有登录，重定向到 login
        if (!initialState?.currentUser && location.pathname !== loginPath) {
          history.push(loginPath);
        }
      }
    },
    links: isDev
      ? [
          <Link key="openapi" to="/umi/plugin/openapi" target="_blank">
            <LinkOutlined />
            <span>OpenAPI 文档</span>
          </Link>,
          <Link key="docs" to="/~docs">
            <BookOutlined />
            <span>业务组件文档</span>
          </Link>,
        ]
      : [],
    menuHeaderRender: undefined,
    menu: {
      // 每当 initialState?.currentUser?.userid 发生修改时重新执行 request
      params: {
        userId: initialState?.currentUser?.userId,
      },
      request: async () => {
        if (!initialState?.currentUser?.userId) {
          return [];
        }
        // initialState.currentUser 中包含了所有用户信息
        const menus = await getRoutersInfo();
        setInitialState((preInitialState) => ({
          ...preInitialState,
          menus,
        }));
        return menus;
      },
    },
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    // 增加一个 loading 的状态
    childrenRender: (children, props) => {
      return (
        <div>
          {children}
          {!props.location?.pathname?.includes('/login') && (
            <SettingDrawer
              enableDarkTheme
              settings={initialState?.settings}
              onSettingChange={(settings) => {
                setInitialState((preInitialState) => ({
                  ...preInitialState,
                  settings,
                }));
              }}
            />
          )}
        </div>
      );
    },
    ...initialState?.settings,
  };
};
