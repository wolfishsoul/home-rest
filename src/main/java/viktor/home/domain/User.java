package viktor.home.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(length = 36, columnDefinition = "varchar(36)")
    private String userName;

    @Column(length = 36, columnDefinition = "varchar(36)")
    private String password;

}
