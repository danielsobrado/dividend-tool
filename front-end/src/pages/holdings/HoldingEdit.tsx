// src/pages/holdings/HoldingEdit.tsx
import * as React from 'react';
import {
  Edit,
  SimpleForm,
  TextInput,
  NumberInput,
  DateInput,
} from 'react-admin';

export const HoldingEdit: React.FC = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput disabled source="id" />
      <TextInput source="ticker" />
      <NumberInput source="quantity" />
      <NumberInput source="averageCost" />
      <DateInput source="purchaseDate" />
    </SimpleForm>
  </Edit>
);
