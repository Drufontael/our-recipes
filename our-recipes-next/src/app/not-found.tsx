'use client'

import { Template } from "@/components"

export default function NotfoundPage(){
    return (
        <Template
            headerActions={{
                listRecipes:true,
            }}
        >
            <section className="text-brown-700 text-center text-xl">Page not found!</section>
        </Template>
      );
}
 