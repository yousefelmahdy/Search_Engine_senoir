/*//get all elements we need
const Search_Box = document.querySelector(".Search_Box");
const Search_Input = document.querySelector("input");
const Sug = document.getElementById("S");
console.log("halooo");

//to realease that the user start type and search
/*
function Show_Sug(str) {
    console.log(Submitted_Links);
    let search_I = str;
    let list = [];
    console.log(search_I);
    if (search_I) //if the user press key
    {
        //make filteration to return only the Sug start with what the user write
        list = Submitted_Links.filter((element) => {
            return element.toLowerCase().startsWith(search_I.toLowerCase());
        });
        //make mapping to make the suggestions being in a list
        list = list.map((element) => {
            return element = '<li>' + element + '</li>';

        });
        Search_Box.classList.add("S_List");
        Sug_List_HTML(list);
        let Butt = document.getElementById("Button");
        Butt.setAttribute("onclick", "select(this)");
        //get all the lists

        let Selectes_Data = document.querySelectorAll("li");
        for (let i = 0; i < Selectes_Data.length; i++) {
            Selectes_Data[i].setAttribute("onclick", "select(this)");
        }
    } else {
        Search_Box.classList.remove("S_List");
    }

}

//}
*/
/*
Search_Input.onkeyup = (ev) => {
    console.log("function invoook");
    let search_I = ev.target.value;
    let list = [];
    console.log(search_I);
    console.log(Submitted_Links);
    if (search_I) //if the user press key
    {
        if (Submitted_Links.length == 0) {
            Sug_List_HTML(Submitted_Links);
            return;
        }
        //make filteration to return only the Sug start with what the user write
        list = Submitted_Links.filter((element) => {
            return element.toLowerCase().startsWith(search_I.toLowerCase());
        });
        //make mapping to make the suggestions being in a list
        list = list.map((element) => {
            return element = '<li>' + element + '</li>';

        });

        Search_Box.classList.add("S_List");
        Sug_List_HTML(list);
        let Butt = document.getElementById("Button");
        Butt.setAttribute("onclick", "select(this)");
        //get all the lists

        let Selectes_Data = document.querySelectorAll("li");
        for (let i = 0; i < Selectes_Data.length; i++) {
            Selectes_Data[i].setAttribute("onclick", "select(this)");
        }


    } else {
        Search_Box.classList.remove("S_List");
    }
}

function Sug_List_HTML(list) {
    let Sug_List;
    if (list.length == 0) {

        Sug_List = '<li>' + '' + '</li>';
    } else {

        Sug_List = list.join('');
        Sug.innerHTML = Sug_List;
    }

}
//this function to select elements when the user press onthem
function select(element) {
    //put the value the user press in the input box 
    Search_Input.value = element.textContent;
    //Submitted_Links.push(Search_Input.value);
    console.log("function invoook");


}
*/