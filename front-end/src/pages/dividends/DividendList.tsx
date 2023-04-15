import * as React from 'react';
import { List, Datagrid, TextField, DateField, NumberField } from 'react-admin';

export const DividendList: React.FC = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="ticker" />
      <TextField source="type" />
      <NumberField source="cash_amount" />
      <DateField source="ex_eff_date" />
      <DateField source="declaration_date" />
      <DateField source="record_date" />
      <DateField source="payment_date" />
      <TextField source="exchange" />
      <TextField source="source" />
    </Datagrid>
  </List>
);
