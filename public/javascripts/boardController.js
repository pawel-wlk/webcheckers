function createField(fieldData) {
    field = document.createElement("div");

    field.innerHTML = "o";

    return field;
}


for (var i=0; i<13*17; i++) {
    document.getElementById("board").appendChild(createField());
}

// document.getElementById("board").innerHTML = "This script is working";