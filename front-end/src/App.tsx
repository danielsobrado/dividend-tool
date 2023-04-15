import * as React from "react";
import { Admin, Resource } from "react-admin";
import { UserList, UserEdit, UserCreate } from "./pages/users";
import { HoldingList, HoldingEdit, HoldingCreate } from "./pages/holdings";
import { DividendList, DividendEdit, DividendCreate } from "./pages/dividends";
import { PortfolioList, PortfolioEdit, PortfolioCreate } from "./pages/portfolios";
import { OperationList, OperationEdit, OperationCreate } from "./pages/operations";
import jsonServerProvider from "ra-data-json-server";

const dataProvider = jsonServerProvider("http://localhost:3000");

const App = () => (
  <Admin dataProvider={dataProvider}>
    <Resource name="users" list={UserList} edit={UserEdit} create={UserCreate} />
    <Resource name="holdings" list={HoldingList} edit={HoldingEdit} create={HoldingCreate} />
    <Resource name="dividends" list={DividendList} edit={DividendEdit} create={DividendCreate} />
    <Resource name="portfolios" list={PortfolioList} edit={PortfolioEdit} create={PortfolioCreate} />
    <Resource name="operations" list={OperationList} edit={OperationEdit} create={OperationCreate} />
  </Admin>
);

export default App;
