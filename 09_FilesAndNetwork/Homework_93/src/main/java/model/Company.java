package model;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@ToString(includeFieldNames = false, exclude = "descriptionFull")
public class Company {

    @Getter
    @NonNull
    private String descriptionFull;
    @Getter
    @NonNull
    private String Code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Code.equals(company.Code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Code);
    }

}
