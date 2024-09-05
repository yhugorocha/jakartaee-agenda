package io.hugodev.jakarta.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.hugodev.jakarta.dao.ContatoDao;
import io.hugodev.jakarta.model.Contato;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type Agenda controller.
 */
@WebServlet(urlPatterns = {"/controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class AgendaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action){
            case "/main": listarContato(request, response); break;
            case "/insert": adicionarContato(request, response); break;
            case "/select": selecionarContato(request, response); break;
            case "/update": editarContato(request, response); break;
            case "/delete": excluirContato(request, response); break;
            case "/report": gerarRelatorio(request, response); break;
            default: response.sendRedirect("index.html"); break;
        }

    }

    /**
     * Listar contato.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ContatoDao dao = new ContatoDao();
        List<Contato> lista = dao.listarContatos();

        request.setAttribute("contatos", lista);
        request.getRequestDispatcher("agenda.jsp").forward(request, response);
    }

    /**
     * Adicionar contato.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void adicionarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Contato contato = new Contato();
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

        ContatoDao dao = new ContatoDao();
        dao.salvar(contato);

        response.sendRedirect("/agenda/main");
    }

    /**
     * Selecionar contato.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void selecionarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ContatoDao dao = new ContatoDao();
        Contato contato = dao.selecionarContato(id);

        request.setAttribute("id", contato.getId().toString());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("email", contato.getEmail());
        request.setAttribute("fone", contato.getFone());

        request.getRequestDispatcher("editarContato.jsp").forward(request, response);
    }

    /**
     * Editar contato.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Contato contato = new Contato();
        contato.setId(UUID.fromString(request.getParameter("id")));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));

        ContatoDao dao = new ContatoDao();
        dao.atualizarContato(contato);

        response.sendRedirect("/agenda/main");
    }

    /**
     * Excluir contato.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void excluirContato(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        ContatoDao dao = new ContatoDao();
        dao.deletarContato(id);

        response.sendRedirect("/agenda/main");
    }

    /**
     * Gerar relatorio.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Document documento = new Document();

        try{
            response.setContentType("apllication/pdf");

            response.addHeader("Content-Disposition", "inline; filename="+"contato.pdf");

            PdfWriter.getInstance(documento, response.getOutputStream());

            documento.open();

            documento.add(new Paragraph("Lista de contatos:"));
            documento.add(new Paragraph("  "));

            PdfPTable tabela = new PdfPTable(3);

            PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
            PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
            PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));

            tabela.addCell(col1);
            tabela.addCell(col2);
            tabela.addCell(col3);

            ContatoDao dao = new ContatoDao();

            List<Contato> contatos = dao.listarContatos();

            for(Contato contato: contatos){
                tabela.addCell(new Paragraph(contato.getNome()));
                tabela.addCell(new Paragraph(contato.getFone()));
                tabela.addCell(new Paragraph(contato.getEmail()));
            }

            documento.add(tabela);

        }catch (Exception e){
            System.out.println(e);
        }finally {
            documento.close();
        }
    }

}
