package ZeusFit.model;

import javax.persistence.*;

@Entity
@Table(name = "ruoli",uniqueConstraints = @UniqueConstraint(columnNames = "nome"))
public class Ruolo {
        @Id
        @Column(name="id")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long id;

        @Column(name="nome",nullable = false)
        private String nome;

        public Ruolo() {

        }

        public Ruolo(String nome) {
            super();
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getNome() {
            return nome;
        }
        public void setName(String nome) {
            this.nome = nome;
        }
    }

