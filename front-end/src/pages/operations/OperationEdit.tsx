// src/pages/operations/OperationEdit.tsx
import * as React from 'react';
import {
  Edit,
  SimpleForm,
  TextInput,
  SelectInput,
  NumberInput,
  ReferenceInput,
  DateInput,
} from 'react-admin';

export const OperationEdit: React.FC = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput disabled source="id" />
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
      <DateInput source="created_at" />
      <DateInput source="updated_at" />
    </SimpleForm>
  </Edit>
);
