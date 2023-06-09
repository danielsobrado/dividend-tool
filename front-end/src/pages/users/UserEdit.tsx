// src/pages/users/UserEdit.tsx
import * as React from 'react';
import {
  Edit,
  SimpleForm,
  TextInput,
  PasswordInput,
  DateInput,
} from 'react-admin';

export const UserEdit: React.FC = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput disabled source="id" />
      <TextInput source="username" />
      <TextInput source="email" type="email" />
      <PasswordInput source="password" />
      <DateInput source="created_at" />
      <DateInput source="updated_at" />
    </SimpleForm>
  </Edit>
);
