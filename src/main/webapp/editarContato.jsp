<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="io.hugodev.jakarta.model.Contato" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Página JSP Inicial</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="images/phone.png" type="image/x-icon">
    <title>Agenda de contatos</title>
</head>
<body>
    <div class="container__agenda">
        <h1>Editar de contatos</h1>
        <div class="formulario">
            <form name="frmContato" action="update" >
                <input type="hidden" name="id" placeholder="id" value="<% out.print(request.getAttribute("id")); %>">
                <input type="text" name="nome" placeholder="Nome" value="<% out.print(request.getAttribute("nome")); %>">
                <input type="email" name="email" placeholder="E-mail" value="<% out.print(request.getAttribute("email")); %>">
                <input type="tel" id="fone" name="fone" placeholder="Fone" value="<% out.print(request.getAttribute("fone")); %>">
                <button type="button" class="btn" onclick="validar()" >Atualizar</button>
            </form>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
    <script type="text/javascript">$("#fone").mask("(00) 00000-0000");</script>
    <script src="scripts/validador.js"></script>
</body>
</html>
