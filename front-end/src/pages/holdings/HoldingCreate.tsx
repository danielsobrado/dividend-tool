// src/pages/holdings/HoldingCreate.tsx
import * as React from 'react';
import {
  Create,
  SimpleForm,
  TextInput,
  NumberInput,
  DateInput,
} from 'react-admin';

export const HoldingCreate: React.FC = (props) => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="ticker" />
      <NumberInput source="quantity" />
      <NumberInput source="averageCost" />
      <DateInput source="purchaseDate" />
    </SimpleForm>
  </Create>
);
