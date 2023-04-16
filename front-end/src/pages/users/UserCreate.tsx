// src/pages/users/UserCreate.tsx
import * as React from 'react';
import {
  Create,
  SimpleForm,
  TextInput,
  PasswordInput,
} from 'react-admin';

export const UserCreate: React.FC = (props) => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="username" />
      <TextInput source="email" type="email" />
      <PasswordInput source="password" />
    </SimpleForm>
  </Create>
);
