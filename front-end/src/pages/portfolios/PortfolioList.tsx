// src/pages/portfolios/PortfolioList.tsx
import * as React from 'react';
import { List, Datagrid, TextField, ReferenceField, DateField } from 'react-admin';

export const PortfolioList: React.FC = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="name" />
      <ReferenceField source="user_id" reference="users">
        <TextField source="username" />
      </ReferenceField>
      <DateField source="created_at" />
      <DateField source="updated_at" />
    </Datagrid>
  </List>
);
