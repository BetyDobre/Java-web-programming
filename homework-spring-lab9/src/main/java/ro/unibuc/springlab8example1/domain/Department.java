package ro.unibuc.springlab8example1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "department",  cascade = CascadeType.ALL)
    List<User> users;
}
