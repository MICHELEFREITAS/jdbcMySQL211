package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			
			//instancio o prepareStatement st usando o comando prepareStatement espera comando sql
			//"?" simboliza o lugar onde vai colocar depois o valor
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)");
			
			//comandos para trocar as "?" pelo que definir. O 1 corresponde ao primeiro "?", nome.
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			//pega a data que fez com o sdf....getTime() e esse valor usar para instanciar o java.sql.Date
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			//chama operação para alterar os dados
			//o resultado da operação é um número inteiro indicando quantas linhas foram alteradas no BD
			int rowsAffected = st.executeUpdate();
			
			//pronto
			System.out.println("Done! Rows affected: " + rowsAffected);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally{
			//fechando comandos, sempre conexão por último
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	
	}

}
