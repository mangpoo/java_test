/*
[accountApp] - 은행입출금관리 프로젝트 생성

[Account class] <<use>> [AccountMgr class]
 (dependency)

1. 계좌를 관리할 수 있는 데이터 클래스 Account 클래스를 생성한다.
2. 계좌에 관한 데이터를 처리하는 클래스 AccountMgr 클래스를 생성한다.
**(데이터와 데이터를 처리하는 클래스는 따로 두어야한다.)

===== 은행 입출금 관리 시스템 =====

1. 계좌계설
2. 입금
3. 출금
4. 잔액조회
5. 전체 계좌 조회
6. 종료
항목을 선택하세요.

프로그램 실행 시 콘솔(Console) 화면에서 번호를 누른 후 각 설계에 맞게 동작하도록 구현하세요.
 */
import java.util.Scanner;
import java.util.HashMap;

class Account{
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}

class AccountMgr{
    private HashMap<String, Account> accounts = new HashMap<>();

    public void createAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new Account(accountNumber));
            System.out.println("계좌가 성공적으로 생성되었습니다.");
        } else {
            System.out.println("이미 존재하는 계좌 번호입니다.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount); // ?
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("출금이 완료되었습니다.");
            } else {
                System.out.println("잔액이 부족합니다.");
            }
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    public void checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("계좌 잔액: " + account.getBalance() + "원");
        } else {
            System.out.println("계좌를 찾을 수 없습니다.");
        }
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
        } else {
            System.out.println("전체 계좌 목록:");
            for (String accountNumber : accounts.keySet()) {
                System.out.println("- 계좌 번호: " + accountNumber + ", 잔액: " + accounts.get(accountNumber).getBalance());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountMgr accountMgr = new AccountMgr();

        while(true) {
            System.out.print("1. 계좌계설\n" +
                    "2. 입금\n" +
                    "3. 출금\n" +
                    "4. 잔액조회\n" +
                    "5. 전체 계좌 조회\n" +
                    "6. 종료\n" +
                    "항목을 선택하세요: ");

            int choice = sc.nextInt();
            sc.nextLine(); // 입력 버퍼 클리어

            switch(choice) { // 스위치문 사용해서 각각 처리
                case 1:
                    System.out.print("만드실 계좌의 번호를 입력해주세요: ");
                    String accountNumber = sc.nextLine();
                    accountMgr.createAccount(accountNumber);
                    break;
                case 2:
                    System.out.print("입금할 계좌의 번호를 입력해주세요: ");
                    accountNumber = sc.nextLine();
                    System.out.print("입금할 금액을 입력해주세요: ");
                    double amount = sc.nextDouble();
                    accountMgr.deposit(accountNumber, amount);
                    break;
                case 3:
                    System.out.print("출금할 계좌의 번호를 입력해주세요: ");
                    accountNumber = sc.nextLine();
                    System.out.print("출금할 금액을 입력해주세요: ");
                    amount = sc.nextDouble();
                    accountMgr.withdraw(accountNumber, amount);
                    break;
                case 4:
                    System.out.print("조회할 계좌의 번호를 입력해주세요: ");
                    accountNumber = sc.nextLine();
                    accountMgr.checkBalance(accountNumber);
                    break;
                case 5:
                    accountMgr.listAccounts();
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}

// equals = 문자열 2개가 같은지를 비교하여 결괏값을 리턴
// 변수.indexof() = 문자열에서 특정 문자열이 시작되는 위치를 리턴
// 변수.contains() = 문자열에서 특정 문자열이 포함되어 있는지 여부를 리턴
// 변수.charAt() = 문자열에서 특정 위치의 문자를 리턴
// 변수.substring() = 문자열에서 특정 문자열을 뽑아냄
// 변수.toUpperCase() = 문자열을 모두 대문자로 변경