package ru.russeb.graduationwork.entity


import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false, length = 100)
    var name: String? = null

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    var slug: String? = null

    @Column(name = "description")
    var description: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Builder.Default
    val subCategories: MutableList<Category?> = ArrayList<Category?>()

    @Column(name = "image_url", length = 500)
    var imageUrl: String? = null

    @Column(name = "sort_order")
    @Builder.Default
    val sortOrder = 0

    @Column(name = "is_active")
    @Builder.Default
    val isActive = true

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null

}