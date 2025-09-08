# Java_Selenium_Projects
Selenium Project

# Automated Login Testing Using Selenium (Java)

## 📌 Description
Automates login testing for:
[https://the-internet.herokuapp.com/login](https://the-internet.herokuapp.com/login)

Checks:
1. Valid login
2. Invalid login
3. Empty fields
4. CSV-driven login attempts

## 📌 Tools & Technologies
- Java 23
- Selenium WebDriver 4.34.0
- ChromeDriver
- Maven

## 📌 Setup Instructions
1. Install Java JDK (23).
2. Install Maven.
3. Add Selenium dependency in `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.seleniumhq.selenium</groupId>
       <artifactId>selenium-java</artifactId>
       <version>4.34.0</version>
   </dependency>

## 📌 Project Structure
Java_Selenium_Project/
│── pom.xml # Maven project configuration
│── src/main/java/LoginAuto.java # Main automation script
│── credentials.csv # CSV file for data-driven testing
│── README.md # Documentation

## 📌 Setup Instructions

### 1. Install Prerequisites
- Install **Java JDK 11+**  
- Install **Maven**  
- Download **ChromeDriver** from:  
  [https://chromedriver.chromium.org/downloads](https://chromedriver.chromium.org/downloads)

---

### 2. Add Selenium Dependency
In your `pom.xml`, include:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.34.0</version>
</dependency>

Then run:
mvn clean install
```

3. Update ChromeDriver Path

Inside LoginAuto.java, update the path:

System.setProperty("webdriver.chrome.driver", "D:\\path\\to\\chromedriver.exe");


Or add ChromeDriver to your system PATH to avoid hardcoding.

4. Create credentials.csv

Place this file in your project root folder:
```
tomsmith,SuperSecretPassword!
wrongusername,wrongpassword
,

```

5. Run the Project

6. Test Cases

✅ Test Case 1: Valid Login

Username: tomsmith

Password: SuperSecretPassword!

Expected: Redirect to Secure Area, success message shown.

✅ Test Case 2: Invalid Login

Username: wrongusername

Password: wrongpassword

Expected: "Your username is invalid!" message shown.

✅ Test Case 3: Empty Fields

Username: (empty)

Password: (empty)

Expected: Error message displayed.

✅ Bonus: CSV-Driven Login Tests

Reads multiple username/password combinations from credentials.csv.

Logs Pass/Fail results for each combination.

📌 Example Console Output
Test Case 1 Passed: Valid Login successful
Test Case 2 Passed: Invalid Login error displayed
Test Case 3 Passed: Empty fields error displayed

📂 Running CSV-driven login tests...
✅ CSV Test Passed: (tomsmith, SuperSecretPassword!)
❌ CSV Test Failed: (wrongusername, wrongpassword)
❌ CSV Test Failed: (, )
