import * as React from 'react';
import { Create, SimpleForm, TextInput, DateInput, NumberInput } from 'react-admin';

export const DividendCreate: React.FC = (props) => (
  <Create {...props}>
    <SimpleForm>
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
  </Create>
);
