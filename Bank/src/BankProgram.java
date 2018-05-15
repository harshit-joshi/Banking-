import java.util.Scanner;

public class BankProgram {
	public static void main(String[] args) 
	{	
		Scanner scanner=new Scanner(System.in);
		int numberOfCustomers=0;
		Bank bank=new Bank();
		Customer[] c=bank.getCustomer();
		while(true)
		{
			System.out.println("Please Enter from below choice");
			System.out.println("1:Add Customer");
			System.out.println("2:Deposit Money");
			System.out.println("3:Withdraw Money");
			System.out.println("4:Check Balance");
			System.out.println("5:Calculate Intrest");
			System.out.println("6:Exit");
			int choice =scanner.nextInt();
				switch(choice)
				{
				case 1:
					System.out.println("Creating an Account for new customer");
					System.out.println("Enter the initial amount in your Account");
					double bal=scanner.nextDouble();
					System.out.println("Please enter Account No of your choice");
					String accountNo=scanner.next();
					Account account=new Account(bal,accountNo);
					System.out.println("Please enter your Name");
					String name=scanner.next();
					Customer customer=new Customer(name,account);
					c[numberOfCustomers]=customer;
					numberOfCustomers++;
					System.err.println("Number of Customers"+numberOfCustomers);
					for(int i=0;i<numberOfCustomers;i++)
					{
						System.err.println(c[i].getName()+"Name");
					}
					break;
				case 2:
					System.out.println("Enter Your Account No");
					accountNo=scanner.next();
					if(numberOfCustomers==0)
					{
						System.out.println("No Customer is there so you cannot deposit money please add Customer first");
					}
					else
					{
						boolean found=false;
						for(int i=0; i< numberOfCustomers;i++)
						{
							Account temp = c[i].getAccount();
							String accTemp =temp.getAccountNo();
							if(accTemp.equals(accountNo))
							{
								System.out.println("Please enter the amount to be deposited");
								double money=scanner.nextDouble();
								temp.deposit(money);
								found=true;
								
							}
						}
						if(found==false)
						{
							System.out.println("Account Number Not found Please Recheck your Account No");
						}
					}
					break;
				case 3:
					System.out.println("Enter Your Account No");
					accountNo=scanner.next();
					if(numberOfCustomers==0)
					{
						System.out.println("No Customer is there so you cannot withdraw money please add Customer first");
					}
					else
					{
						boolean found=false;
						for(int i=0; i< numberOfCustomers;i++)
						{
							Account temp = c[i].getAccount();
							String accTemp =temp.getAccountNo();
							if(accTemp.equals(accountNo))
							{
								System.out.println("Please enter the amount to be withdraw");
								double money=scanner.nextDouble();
								temp.withdraw(money);
								found=true;
								
							}
						}
						if(found==false)
						{
							System.out.println("Account Number Not found Please Recheck your Account No");
						}
					}
					break;
				case 4:
					System.out.println("Enter Your Account No");
					accountNo=scanner.next();
					if(numberOfCustomers==0)
					{
						System.out.println("No Customer is there  please add Customer first");
					}
					else
					{
						boolean found=false;
						for(int i=0; i< numberOfCustomers;i++)
						{
							Account temp = c[i].getAccount();
							String accTemp =temp.getAccountNo();
							if(accTemp.equals(accountNo))
							{
								System.out.println("Balance is :"+temp.getBalance());
								
								found=true;
								
							}
						}
						if(found==false)
						{
							System.out.println("Account Number Not found Please Recheck your Account No");
						}
					}
					break;
				case 5:
					System.out.println("Enter Your Account No");
					accountNo=scanner.next();
					if(numberOfCustomers==0)
					{
						System.out.println("No Customer is there  please add Customer first");
					}
					else
					{
						boolean found=false;
						for(int i=0; i< numberOfCustomers;i++)
						{
							Account temp = c[i].getAccount();
							String accTemp =temp.getAccountNo();
							if(accTemp.equals(accountNo))
							{
								bank.calculateIntrest(c[i]);
								found=true;
								
							}
						}
						if(found==false)
						{
							System.out.println("Account Number Not found Please Recheck your Account No");
						}
					}
					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("Ennter choice between 1 to 6");
					break;
					
				}
		}
		
	}
}
class Bank
{
	private double intrestRate=8.5;
	private double transcationFees=10;
	private Customer[] customers=new Customer[1000];
	public void calculateIntrest(Customer customer)
	{
		Account a=customer.getAccount();
		double bal=a.getBalance();
		double intrestAmount=(bal*intrestRate)/100;
		double totalAmount=bal+intrestAmount;
		System.out.println("Intrest amount = "+intrestAmount +"Total amount in Account = "+ totalAmount);
	}
	public double getIntrestRate()
	{
		return intrestRate;
	}
	public double getTranscationFees()
	{
		return transcationFees;
	}
	public Customer[] getCustomer()
	{
		return customers;
	}
}
class Account
{
	private double balance=100;
	private String accountNumber;
	private boolean firstTime=true;
	public Account(String acc)
	{
		accountNumber=acc;
	}
	public Account(Double bal,String acc)
	{
		if (bal>=100)
		{
			balance=bal;
		}
		else
		{
			balance=100;
		}
		accountNumber=acc;
	}
	public void deposit(double howMuch)
	{
		if (howMuch>0)
		{
			balance=balance+howMuch;
			System.out.println(howMuch + " is deposited in your account" +" New Balance in Your Account = "+balance);
		}
		else
		{
			System.err.println("Please enter positive amount to deposit");
		}
	}
	public void withdraw(double howMuch)
	{
		if (howMuch>0)
		{
			if (firstTime==true)
			{
				
				if (howMuch <= (balance - 100))
				{
					balance=balance-howMuch;
					System.out.println(howMuch + " is withdrawn from your account" +" New Balance in Your Account = "+balance);
				}
				else
				{
					System.err.println("Hey you are trying to withdraw more amount then minimum Balance");
				}
				firstTime = false;
			}
			
			else
			{
				Bank bank=new Bank();
				if (howMuch <= (balance -bank.getTranscationFees() - 100))
					{
					balance=balance-howMuch -bank.getTranscationFees();
					System.out.println(howMuch + " is withdrawn from your account" +" New Balance in Your Account = "+balance);
					}
				else
					{
					System.err.println("Hey you are trying to withdraw more amount then minimum Balance");
					}
			}
		}	
		else
		{
			System.err.println("Hey you are Here to withdraw So please enter positive Amount");
		}
		
	}	
	public double getBalance()
	{
		return balance;
	}
	public String getAccountNo()
	{
		return accountNumber;
	}
}
class Customer
{
	private String name;
	private Account account;
	Customer(String n,Account ac)
	{
		name=n;
		account=ac;
	}
	public void display()
	{
		System.out.println("Name of the customer is"+name +"Account No of "+name  +" = "+account.getAccountNo() +"Account Balance is"+account.getBalance());
	}
	public String getName()
	{
		return name;
	}
	public Account getAccount()
	{
		return account;
	}
}