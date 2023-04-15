// src/pages/users/UserList.tsx
import * as React from 'react';
import { List, Datagrid, TextField, EmailField, DateField } from 'react-admin';

export const UserList: React.FC = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="username" />
      <EmailField source="email" />
      <DateField source="created_at" />
      <DateField source="updated_at" />
    </Datagrid>
  </List>
);
