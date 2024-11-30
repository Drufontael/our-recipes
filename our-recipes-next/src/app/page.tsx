'use client'

import { useUserService } from "@/resource/user/user.service";
import Login from "./login/page";
import List from "./list/page";


export default function Home() {

  const auth=useUserService();

  if(auth.getUser()){
    return <List/>
  };

  return (
    <Login/>
  );
}
