

function validar(){
    let nome = frmContato.nome.value
    let email = frmContato.email.value
    let fone = frmContato.fone.value

    if(nome === "" || email === "" || fone === ""){
        alert("È obrigatorio preencher todos os campos!")
        return false;
    }
    document.forms["frmContato"].submit()

}