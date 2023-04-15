// src/pages/operations/OperationCreate.tsx
import * as React from 'react';
import {
  Create,
  SimpleForm,
  TextInput,
  SelectInput,
  NumberInput,
  ReferenceInput,
} from 'react-admin';

export const OperationCreate: React.FC = (props) => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="ticker" />
      <SelectInput source="operation_type" choices={[
        { id: 'buy', name: 'Buy' },
        { id: 'sell', name: 'Sell' },
      ]} />
      <NumberInput source="quantity" />
      <NumberInput source="price" />
      <ReferenceInput source="portfolio_id" reference="portfolios">
        <SelectInput optionText="name" />
      </ReferenceInput>
    </SimpleForm>
  </Create>
);
