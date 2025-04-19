# Dictionary Project

This is a Java-based Dictionary Project that allows users to search for word definitions, manage a dictionary, and create personalized notes. The project includes features like user authentication, word suggestions, and frequently searched word tracking.
Project made using JAVA, Database : PostgreSQl, DS

## Features

- User registration and login system.
- Search for word definitions, synonyms, antonyms, hypernyms, and hyponyms.
- Create and manage personalized word notes.
- Suggest new words to the dictionary.
- Track frequently searched words.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher.
- MySQL database server.
- Visual Studio Code with Java extensions installed.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/dictionary-project.git
   ```
2. Open the project in Visual Studio Code.
3. Configure the MySQL database:
   - Create a database named `dictionary_db`.
   - Update the database credentials in `Dictionary.java`.
4. Build the project:
   ```bash
   javac -d bin src/**/*.java
   ```
5. Run the project:
   ```bash
   java -cp bin LoginRegister
   ```

## Folder Structure

The workspace contains the following folders:

- `src`: Contains the source code.
- `lib`: Contains external libraries (e.g., MySQL connector).
- `bin`: Contains compiled `.class` files.
- `.vscode`: Contains VS Code configuration files.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## Contact

For any inquiries, please contact [your-email@example.com].
