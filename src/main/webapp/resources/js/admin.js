async function fetchProducts(){
    let json
    let config = {
        method : "GET",
        header : {
            'content-type': 'application/json', 
        },
    }
    try{
        let url = "http://localhost:8080/FOODHUB/products";
        let response = await fetch(url,config);
        if(response.ok){
            json = await response.json();
        }else{
            console.log("could not fetch data");
        }
    }
    catch(e){
        console.log("exception");
    }
    return json;
}


function displayBreakfastToAdmin(data){
    let tableelem = document.querySelector("table")
    for(let product of data){


        let name = product.pname
        let image = product.realImage
        let description = product.pdesc
        let cost = product.pcost
        let id = product.pid


        let trowelem = document.createElement("tr")
        let itemid = document.createElement("td")
        let itemname = document.createElement("td")
        let itemcost =  document.createElement("td")
        let itemdesc = document.createElement("td")
        let itemimg = document.createElement("td")
        let itemupdate = document.createElement("td")
        let itemdelet = document.createElement("td")

        itemupdate.innerHTML = "<a id = "+id+" href = '#'>UPDATE</a>"
        itemdelet.innerHTML = "<a id = "+id+" href = '#'>DELETE</a>"
        itemupdate.setAttribute("id","update")
        itemupdate.addEventListener("click",updateProduct)

        itemdelet.setAttribute("id","delete")
        itemdelet.addEventListener("click",deleteProduct)

        itemid.innerText = id+"";
        itemname.innerText = name+""
        itemcost.innerText = cost+" rs/-"
        itemdesc.innerText = description+""
        itemimg.innerText = "coming soon"


        trowelem.appendChild(itemid)
        trowelem.appendChild(itemname)
        trowelem.appendChild(itemcost)
        trowelem.appendChild(itemdesc)
        trowelem.appendChild(itemimg)
        trowelem.appendChild(itemupdate)
        trowelem.appendChild(itemdelet)

        tableelem.appendChild(trowelem)

    }
  
}

function updateProduct(event){
    let updatelem = event.target;
    console.log(updatelem)
    let id = updatelem.getAttribute("id")
    console.log(id)
    // let response = await fetch("http://localhost:8080/FOODHUB/updateProduct", {
    //     method: 'POST',
    //     headers: {
    //       'Content-Type': 'application/json;charset=utf-8'
    //     },
    //     body: JSON.stringify(id)
    //   });
    //   let result = await response.json();
    //   console.log(result.message);
}

async function deleteProduct(event){
    let deleteelem = event.target;
    let id = deleteelem.getAttribute("id")
    let res = confirm("if you hit ok item will be permanently deleted")
    if(res){
        let response = await fetch("http://localhost:8080/FOODHUB/deleteProduct", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(id)
      });
    }
    let data = await fetchProducts();
    displayBreakfastToAdmin(data);
}

async function main(){
    let data = await fetchProducts();
    displayBreakfastToAdmin(data);
}
main();
