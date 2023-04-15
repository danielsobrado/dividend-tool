// src/pages/users/UserCreate.tsx
import * as React from 'react';
import {
  Create,
  SimpleForm,
  TextInput,
  EmailInput,
  PasswordInput,
} from 'react-admin';

export const UserCreate: React.FC = (props) => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="username" />
      <EmailInput source="email" />
      <PasswordInput source="password" />
    </SimpleForm>
  </Create>
);
