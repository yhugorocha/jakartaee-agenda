<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="io.hugodev.jakarta.model.Contato" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agenda de contados</title>
    <link rel="stylesheet" href="style.css">
    <link rel="icon" href="images/phone.png" type="image/x-icon">
</head>
<body>
    <div class="container__agenda">
        <h1>Agenda de Contatos</h1>
        <a href="novoContato.html" class="btn">Novo Contato</a>
        <a href="report" class="btn">Gerar Relatório</a>
        </br>
        </br>
        <div class="container__tabela">
            <table>
                <tr class='table__tr'>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Fone</th>
                    <th>E-mail</th>
                    <th>Ações</th>
                </tr>
                <%
                   List<Contato> contatos = (List<Contato>) request.getAttribute("contatos");
                   for (Contato contato : contatos) {
                        out.println("<tr class='table__tr'>");
                        out.println("<td>" + contato.getId() + "</td>");
                        out.println("<td>" + contato.getNome() + "</td>");
                        out.println("<td>" + contato.getFone() + "</td>");
                        out.println("<td>" + contato.getEmail() + "</td>");
                        out.println("<td> <a class='btn_atualizar' href='select?id="+contato.getId()+"'>Atualizar</a><a class='btn_remover' href='delete?id="+contato.getId()+"'>Deletar</a></td>");
                        out.println("</tr>");
                   }
                 %>

            </table>

        </div>
    </div>
</body>
</html>
