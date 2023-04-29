import React, { useEffect, useMemo, useState } from 'react';
import {
  ProFormDigit,
  ProFormText,
  ProFormRadio,
  ProFormTreeSelect,
  ProFormSelect,
} from '@ant-design/pro-form';
import { Form, Modal, Row, Col } from 'antd';
import { useIntl, FormattedMessage } from 'umi';
import { usePlugins } from '@amap/amap-react';

/* *
 *
 * @author whiteshader@163.com
 * @datetime  2021/09/16
 *
 * */

export type DeptFormValueType = Record<string, unknown> & Partial<API.WarehouseUpdateRequest>;

export type DeptFormProps = {
  onCancel: (flag?: boolean, formVals?: DeptFormValueType) => void;
  onSubmit: (values: DeptFormValueType) => Promise<void>;
  visible: boolean;
  values: Partial<API.WarehouseUpdateRequest>;
  deptTree: any;
  statusOptions: any;
};

const DeptForm: React.FC<DeptFormProps> = (props) => {
  const [form] = Form.useForm();
  const [options, setOptions] = useState<[]>([]);
  const [searchload, setSearchload] = useState(false);
  const [searCity, setSearCity] = useState<[]>([]);

  const { statusOptions, deptTree } = props;
  const AMap = usePlugins(['AMap.AutoComplete']);
  const ac = useMemo(() => {
    if (AMap) return new AMap.AutoComplete();
    else return null;
  }, [AMap]);
  const handleSearch = async (kw: any) => {
    if (!ac) return;
    if (kw === null || kw.keyWords == undefined) {
      return;
    }
let datt
    await ac.search(kw.keyWords, (status: string, result: any) => {
      setSearchload(true);
      console.info('message');
      console.info(status);
      console.info(result);
      if (status === 'complete' && result.tips) {
        console.info('ok');
        setOptions(result.tips);
        datt=result.tips;
        return result.tips;
      } else {
        setOptions([]);
        return [];
      }
    });
    // console.info("data");
    // console.info(data);
    // console.info(options);
    //  data;
    return datt;
  };
  useEffect(() => {
    form.resetFields();
    form.setFieldsValue({
      id: props.values.id,
      parentId: props.values.parentId,
      warehouseNaem: props.values.warehouseName,
      warehouseAddress: props.values.warehouseAddress,
      code: props.values.code,
      warehouseAdminId: props.values.warehouseAdminId,
      phone: props.values.phone,
    });
  }, [form, props]);

  const handleOnChange = (value: any) => {
    console.info("changene")
    console.info(value);
  };
  const intl = useIntl();
  const handleOk = () => {
    form.submit();
  };
  const handleCancel = () => {
    props.onCancel();
    form.resetFields();
  };
  const handleFinish = async (values: Record<string, any>) => {
    props.onSubmit(values as DeptFormValueType);
  };

  return (
    <Modal
      width={640}
      title={intl.formatMessage({
        id: 'system.Dept.modify',
        defaultMessage: '编辑Warehouse',
      })}
      visible={props.visible}
      destroyOnClose
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <Form form={form} onFinish={handleFinish} initialValues={props.values}>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormTreeSelect
              name="parentId"
              label="上级仓库:"
              request={async () => {
                return deptTree;
              }}
              fieldProps={{
                fieldNames: { label: 'warehouseName', value: 'id', children: 'children' },
              }}
              width="xl"
              placeholder="请选择上级仓库"
              rules={[
                {
                  required: true,
                  message: '请选择上级部门!',
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormText
              name="ancestors"
              label={intl.formatMessage({
                id: 'system.Dept.ancestors',
                defaultMessage: '祖级列表',
              })}
              width="xl"
              placeholder="请输入祖级列表"
              hidden={true}
              rules={[
                {
                  required: false,
                  message: (
                    <FormattedMessage id="请输入祖级列表！" defaultMessage="请输入祖级列表！" />
                  ),
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormDigit
              name="code"
              label="code"
              width="xl"
              placeholder="请输入code代码"
              rules={[
                {
                  required: true,
                  message: <FormattedMessage id="请输入Code！" defaultMessage="请输入Code！" />,
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormText
              name="warehouseName"
              label="仓库名称"
              width="xl"
              placeholder="请输入部门名称"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage id="请输入仓库名称！" defaultMessage="请输入仓库名称！" />
                  ),
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormSelect
              showSearch
              name="warehouseAddress"
              label="仓库地址"
              width="xl"
              placeholder="请输入仓库地址"
              request={handleSearch}
              options={options}
              debounceTime={1000}
              fieldProps={{
                onChange: handleOnChange,
                fieldNames: { label: 'district', value: 'district' },
                loading: searchload,
              }}
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage id="请输入仓库地址！" defaultMessage="请输入仓库地址！" />
                  ),
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            <ProFormText
              name="phone"
              label={intl.formatMessage({
                id: 'system.Dept.phone',
                defaultMessage: '联系电话',
              })}
              width="xl"
              placeholder="请输入联系电话"
              rules={[
                {
                  required: true,
                  message: (
                    <FormattedMessage id="请输入联系电话！" defaultMessage="请输入联系电话！" />
                  ),
                },
              ]}
            />
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={24} order={1}>
            {/* <ProFormRadio.Group
              valueEnum={statusOptions}
              name="warehouseAdminId"
              label="负责人"
              labelCol={{ span: 24 }}
              width="xl"
              placeholder="请选择部门负责人"
              rules={[
                {
                  required: false,
                  message: (
                    <FormattedMessage id="请选择部门负责人！" defaultMessage="请选择部门负责人！" />
                  ),
                },
              ]}
            /> */}
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default DeptForm;
