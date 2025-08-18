document.getElementById("addEmployeeForm").addEventListener("submit", function(event){
    event.preventDefault();

    const name = document.getElementById("fname").value;
    const age = document.getElementById("fage").value;
    const gender = document.getElementById("fgender").value; 
    const nation = document.getElementById("fnation").value;

    const tr = document.createElement("tr");
    tr.innerHTML=`
    <td>${name}</td>
    <td>${age}</td>
    <td>${gender}</td>
    <td>${nation}</td>
    `;

    document.getElementById("detail").appendChild(tr);

    document.getElementById("addEmployeeForm").reset();
});