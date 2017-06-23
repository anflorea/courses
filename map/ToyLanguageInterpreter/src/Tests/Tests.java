package Tests;

/**
 * Created by flo on 2016-11-02.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.Test;

import Controller.Controller;
import Model.*;
import Repository.IRepo;
import Repository.Repo;
import Utils.ExeStack;
import Utils.Out;
import Utils.SymTable;


public class Tests {
	/*
    @Test
  public void test1() {

	  IRepo repo = new Repo();
	  Controller ctrl = new Controller(repo);
	  PrgState prgState;

	  IStmt statement = new CompStmt(
				new AssignStmt("v", new ConstExp(5)),
				new PrintStmt(new VarExp("v")));
	  prgState = new PrgState(
				new ExeStack<IStmt>(),
				new SymTable<String, Integer>(),
				new Out<Integer>(),
				statement);
	  repo.addProgramState(prgState);

	  try
	  {
		  ctrl.executeAll();
		  assertEquals(prgState.getOutput().toString(), "[5]");

	  }
	  catch(CustomException mess)
	  {
		  System.out.println(mess.getMessage());
	  }
  }



    public void test2() {
	  IRepo repo = new Repo();//metoda de setup
	  Controller ctrl = new Controller(repo);
	  PrgState prgState;

	  IStmt statement = new CompStmt(
				new AssignStmt("a",
						new ArithExp('+', new ConstExp(8), new	ArithExp('/', new ConstExp(4), new ConstExp(2)))),
				 new CompStmt(new AssignStmt("b",new ArithExp('+', new VarExp("a"), new ConstExp(12))),
						 new PrintStmt(new VarExp("b"))));
	  prgState = new PrgState(
				new ExeStack<IStmt>(),
				new SymTable<String, Integer>(),
				new Out<Integer>(),
				statement);
	  repo.addProgramState(prgState);

	  try
	  {
		  ctrl.executeAll();
		  assertEquals(prgState.getOutput().toString(), "[22]");
	  }
	  catch(CustomException mess)
	  {
		  System.out.println(mess.getMessage());
	  }
  }

  public void test3() {
	  IRepo repo = new Repo();
	  Controller ctrl = new Controller(repo);
	  PrgState prgState;

	  IStmt statement = new CompStmt(
				new AssignStmt("a", new ArithExp('/',
						new ConstExp(8), new ConstExp(2))),
				new CompStmt(
						new IfStmt(new VarExp("a"),
								new AssignStmt("v",new ConstExp(1)),
								new AssignStmt("v", new ConstExp(2))),
						new PrintStmt(new VarExp("v"))));
	  prgState = new PrgState(
				new ExeStack<IStmt>(),
				new SymTable<String, Integer>(),
				new Out<Integer>(),
				statement);
	  repo.addProgramState(prgState);

	  try
	  {
		  ctrl.executeAll();
		  assertEquals(prgState.getOutput().toString(), "[1]");
	  }
	  catch(CustomException mess)
	  {
		  System.out.println(mess.getMessage());
	  }
  }
  */

}