package agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Agenda 
{
    
ArrayList<String> agenda = new  ArrayList();
int opc;

public void importar(ArrayList<String>agenda)
{
        try
        {
            File arq = new File("Agenda.txt");
            if (!arq.exists()) 
            {
                arq.createNewFile();
            }
            
            FileReader dados = new FileReader("Agenda.txt");
            BufferedReader lerDados = new BufferedReader(dados);
            String linha =  lerDados.readLine();
            
            while (linha != null)
            {
             agenda.add(linha);
             linha = lerDados.readLine();
            }
            lerDados.close();
        }catch (IOException ex)
        {
         JOptionPane.showMessageDialog(null, "Deu erro, meu consagrado "+
         ex.getMessage());
        }
    }
public void exportar(ArrayList<String>agenda) throws IOException
{
    FileWriter dados = new FileWriter("Agenda.txt");
    PrintWriter gravarDados = new PrintWriter(dados);
    int n = agenda.size();
    
    for (int i = 0; i < n; i++) 
    {
        gravarDados.printf("%s%n", agenda.get(i));
    }
    gravarDados.close();
}
public void incluir()
{ 
    String nome, fone;
    nome = JOptionPane.showInputDialog("Digite o nome: ");
    fone = JOptionPane.showInputDialog("Digite o telefone: ");
    agenda.add("Nome: " +nome+ " / Telefone: " + fone);
} 
public void menu()
      
{
      importar(agenda);
    do 
    {
     opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:"
             +"\n1. Incluir contato"
             +"\n2. Excluir contato"
             +"\n3. Listar contatos"
             +"\n4. Pesquisar"
             +"\n0. Sair"));    
     switch(opc)
     {
         case 1: 
             incluir();
             break;
         case 2: 
             excluir();
             break;  
         case 3: 
             listar();
             break;
         case 4: 
             pesquisa(agenda);
             break;
     } 
    
       
}
    while (opc != 0);
    
     try
        {
            exportar(agenda);
            
        }catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Erro ao exportar o arquivo "+
         ex);
        }
    
}
public void excluir()
{
    int busca =  Integer.parseInt(JOptionPane.showInputDialog("Digite o indice desejado: "));
    try 
    {
       agenda.remove(busca-1);
       JOptionPane.showMessageDialog(null, "Indice '"+busca+ "' excluido com sucesso!");
    } 
    catch (Exception e) 
    {
      JOptionPane.showMessageDialog(null, "ID '" +busca+ "' não encontrado.");  
    }
}

public void pesquisa(ArrayList<String>agenda)
{
    String busca = JOptionPane.showInputDialog("Digite o nome do contato que deseja buscar: ").toUpperCase();
    String dados[];
    for (int i = 0; i < agenda.size(); i++) 
    {
        if (agenda.get(i).toUpperCase().indexOf(busca) != -1) 
        {
            dados = agenda.get(i).split(";");
            System.out.println("Nome: "+dados[0]);
        }
        
    }
}
public void listar()
{
    for (int i = 0; i < agenda.size(); i++) 
    {
        System.out.println("Posição: " + (i+1) + " "+agenda.get(i));
    }
}
}




