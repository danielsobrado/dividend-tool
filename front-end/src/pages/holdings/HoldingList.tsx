// src/pages/holdings/HoldingList.tsx
import * as React from 'react';
import {
  List,
  Datagrid,
  TextField,
  NumberField,
  DateField,
  EditButton,
  DeleteButton,
} from 'react-admin';

export const HoldingList: React.FC = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="ticker" />
      <NumberField source="quantity" options={{ minimumFractionDigits: 2 }} />
      <NumberField source="averageCost" options={{ minimumFractionDigits: 2 }} />
      <DateField source="purchaseDate" />
      <EditButton basePath="/holdings" />
      <DeleteButton basePath="/holdings" />
    </Datagrid>
  </List>
);
