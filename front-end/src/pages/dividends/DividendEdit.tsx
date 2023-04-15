import * as React from 'react';
import { Edit, SimpleForm, TextInput, DateInput, NumberInput } from 'react-admin';

export const DividendEdit: React.FC = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput disabled source="id" />
      <TextInput source="ticker" />
      <TextInput source="type" />
      <NumberInput source="cash_amount" />
      <DateInput source="ex_eff_date" />
      <DateInput source="declaration_date" />
      <DateInput source="record_date" />
      <DateInput source="payment_date" />
      <TextInput source="exchange" />
      <TextInput source="source" />
    </SimpleForm>
  </Edit>
);
