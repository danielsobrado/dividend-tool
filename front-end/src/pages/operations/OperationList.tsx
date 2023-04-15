// src/pages/operations/OperationList.tsx
import * as React from 'react';
import {
  List,
  Datagrid,
  TextField,
  NumberField,
  ReferenceField,
  DateField,
} from 'react-admin';

export const OperationList: React.FC = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="ticker" />
      <TextField source="operation_type" />
      <NumberField source="quantity" />
      <NumberField source="price" />
      <ReferenceField source="portfolio_id" reference="portfolios">
        <TextField source="name" />
      </ReferenceField>
      <DateField source="created_at" />
      <DateField source="updated_at" />
    </Datagrid>
  </List>
);