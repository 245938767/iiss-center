import notice from '@/locales/en-US/notice';
import { getByAssetId, getByBatchNo } from '@/services/warehouse/assetController';
import type { ModalBaseProps } from '@/types';
import ProDescriptions from '@ant-design/pro-descriptions';
import type { ProFormInstance } from '@ant-design/pro-form';
import { DrawerForm } from '@ant-design/pro-form';
import Skeleton from '@ant-design/pro-skeleton';
import type { ProTableProps } from '@ant-design/pro-table';
import { Button, Card } from 'antd';
import type { RowSelectionType } from 'antd/lib/table/interface';
import React, { useCallback, useMemo, useRef, useState } from 'react';
import { INPUT_WAREHOUSE_TYPE_MAP, OUTPUT_WAREHOUSE_PRODUCT_TYPE_MAP } from '../constants';
import InputWarehouseTable from './InputWarehouseTable';
import OutputWarehouseTable from './OutputWarehouseTable';
import TableHeader from './TableHeader';

type InputWarehouseSheetProps = {
  recordId: React.Key;
  selection?: {
    type: RowSelectionType;
  };
} & ModalBaseProps;

const TABLE_NAME = 'warehouseAgreeProductList';

const WarehouseSheet: React.FC<InputWarehouseSheetProps> = (props) => {
  const [loading, setLoading] = React.useState(true);
  const [dataSource, setDataSource] = React.useState<any>();
  const formRef = useRef<ProFormInstance>();
  const [selectedRows, setSelectedRows] = useState<any[]>([]);

  React.useEffect(() => {
    if (!props.visible) {
      setDataSource(undefined);
      setLoading(true);
      return;
    }
    getByAssetId({ batchNo: props.recordId } as API.getByBatchNoParams).then((resp) => {
      if (!resp) return;
      setDataSource(resp.result);
      formRef.current?.setFieldsValue({ [TABLE_NAME]: resp.result.assetRecordList });
      setLoading(false);
    });
  }, [props.recordId, props.visible]);

  const submitterRender = useCallback(() => {
    if (props.selection) {
      return (
        <Button
          type="primary"
          onClick={() => {
            props?.onVisibleChange?.(false);
            props?.onDone?.(selectedRows);
          }}
        >
          确定
        </Button>
      );
    }

    return (
      <Button type="primary" onClick={() => props?.onVisibleChange?.(false)}>
        返回
      </Button>
    );
  }, [props, selectedRows]);

  const rowSelectionProps = useMemo<ProTableProps<any, any>['rowSelection']>(() => {
    if (!props.selection) return undefined;
    return {
      type: props.selection.type,
      onChange(keys, rows) {
        setSelectedRows(rows);
      },
    };
  }, [props.selection]);

  return (
    <DrawerForm
      width={1000}
      visible={props.visible}
      formRef={formRef}
      onVisibleChange={props.onVisibleChange}
      drawerProps={{
        maskClosable: false,
        destroyOnClose: true,
      }}
      submitter={{ render: submitterRender }}
    >
      {loading ? (
        <Skeleton type="descriptions" />
      ) : (
        <>
          {/* {notice} */}
          <Card>
            <ProDescriptions column={4} title="仓库信息" dataSource={dataSource}>
              <ProDescriptions.Item label="单号" dataIndex={'batchNo'} valueType="text" />
              <ProDescriptions.Item label="仓库名称" dataIndex={'warehouseName'} valueType="text" />
              <ProDescriptions.Item
                label="商品来源"
                dataIndex={'inOutTypeName'}
                valueType="text"
              />
              <ProDescriptions.Item
                label="商品属性"
                dataIndex={'inOutBizTypeName'}
                valueType="text"
              />
              {/* {dataSource.warehouseProductAdjustName && (
                <ProDescriptions.Item
                  label="调往仓库"
                  dataIndex={['warehouseProductAdjustName', 'to']}
                  valueType="text"
                />
              )} */}
            </ProDescriptions>
          </Card>
          <br />
          <Card title={<TableHeader title="商品表" tableName={[TABLE_NAME]} />}>
            {dataSource.inOutType==="IN" ? (
              <InputWarehouseTable
                name={TABLE_NAME}
                recordCreatorProps={false}
                optionClomuns={false}
                rowSelection={rowSelectionProps}
              />
            ) : (
              <OutputWarehouseTable
                name={TABLE_NAME}
                recordCreatorProps={false}
                warehouseType={dataSource.inOutBizType}
                optionClomuns={false}
                rowSelection={rowSelectionProps}
              />
            )}
          </Card>
          <br />
        </>
      )}
    </DrawerForm>
  );
};

export default WarehouseSheet;
