let myform = document.querySelector("#myform")
console.log(myform)
myform.onsubmit = async (e) => {
    e.preventDefault();

    let response = await fetch("http://localhost:8080/FOODHUB/addProduct", {
      method: 'POST',
      body: new FormData(myform)
    });
  };