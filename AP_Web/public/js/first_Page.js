//get all element we need
const Search_Box = document.querySelector(".Search_Box");
const Search_Input = document.querySelector("input");
const Sug = document.querySelector(".suggestions");
console.log("halooo");

//to realease that the user start type and search
Search_Input.onkeyup = (ev) => {
    let search_I = ev.target.value;
    let list = [];
    if (search_I) //if the user press key
    {
        
        //make filteration to return only the Sug start with what the user write
        list = Sug.filter((element) => {
            return element.toLocaleLowerCase().startswith(search_I.toLocaleLowerCase());
        });
        //make mapping to make the suggestions being in a list
        list = list.map((element) => {
            return element = '<li>' + element + '<li>';

        });
        console.log(list);
        Search_Box.classList.add("S_List");

    } else {

    }
    Sug_List_HTML(list);

}

function Sug_List_HTML(list) {
    let Sug_List;
    if (!list.length) {
        Sug_List = list.join('');
    } else {

    }
    Sug.html(Sug_List);

}