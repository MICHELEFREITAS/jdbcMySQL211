package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			/*
			//instancio o prepareStatement st usando o comando prepareStatement espera comando sql
			//"?" simboliza o lugar onde vai colocar depois o valor
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);//inserção com recuperação do id inserido.Acrescenta valor após String total
			
			//comandos para trocar as "?" pelo que definir. O 1 corresponde ao primeiro "?", nome.
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			//pega a data que fez com o sdf....getTime() e esse valor usar para instanciar o java.sql.Date
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			*/
			
			//inserindo dois departamentos na tabela departament
			st = conn.prepareStatement(
					"INSERT INTO department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			//chama operação para alterar os dados
			//o resultado da operação é um número inteiro indicando quantas linhas foram alteradas no BD
			int rowsAffected = st.executeUpdate();
			
			//caso alguma linha alterada 
			if(rowsAffected >0) {
				ResultSet rs = st.getGeneratedKeys();//pode retornar rs com um ou mais valores
				
				while(rs.next()) {
					//O 1 indica valor da primeira coluna
					int id = rs.getInt(1);//tab. auxiliar que terá só uma coluna contendo os id
					
					System.out.println("Done! Id= " + id);
				}
				
			}else {
				System.out.println("No row affected!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		/*catch(ParseException e) {
			e.printStackTrace();
		}*/
		finally{
			//fechando comandos, sempre conexão por último
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	
	}

}
