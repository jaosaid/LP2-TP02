import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Aluno {
    private UUID id;
    private String nome;
    private String endereco;
    private int idade;

    public Aluno(String nome, String endereco, int idade) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }
}

public class CadastroAlunos extends JFrame {
    private List<Aluno> alunos;
    private JTextField nomeField;
    private JTextField enderecoField;
    private JTextField idadeField;
    private JTextArea resultadoArea;

    public CadastroAlunos() {
        alunos = new ArrayList<>();

        setTitle("Cadastro de Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel nomeLabel = new JLabel("Nome do Aluno:");
        nomeField = new JTextField(20);
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoField = new JTextField(20);
        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField(5);
        JButton okButton = new JButton("OK");
        JButton limparButton = new JButton("Limpar");
        JButton mostrarButton = new JButton("Mostrar");
        JButton sairButton = new JButton("Sair");

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarAlunos();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(enderecoLabel);
        panel.add(enderecoField);
        panel.add(idadeLabel);
        panel.add(idadeField);
        panel.add(okButton);
        panel.add(limparButton);
        panel.add(mostrarButton);
        panel.add(sairButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void cadastrarAluno() {
        String nome = nomeField.getText();
        String endereco = enderecoField.getText();
        String idadeText = idadeField.getText();
        
        if (!nome.isEmpty() && !endereco.isEmpty() && !idadeText.isEmpty()) {
            try {
                int idade = Integer.parseInt(idadeText);
                Aluno aluno = new Aluno(nome, endereco, idade);
                alunos.add(aluno);
                JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
                limparCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um número válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        enderecoField.setText("");
        idadeField.setText("");
    }

    private void mostrarAlunos() {
        StringBuilder mensagem = new StringBuilder("Lista de Alunos:\n");
        for (Aluno aluno : alunos) {
            mensagem.append("ID: ").append(aluno.getId()).append(", Nome: ").append(aluno.getNome())
                    .append(", Endereço: ").append(aluno.getEndereco()).append(", Idade: ").append(aluno.getIdade()).append("\n");
        }
        JOptionPane.showMessageDialog(this, mensagem.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroAlunos());
    }
}
