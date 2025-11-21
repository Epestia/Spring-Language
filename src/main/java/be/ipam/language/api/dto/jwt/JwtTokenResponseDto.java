package be.ipam.language.api.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenResponseDto {
    private String token;
    private String tokenType = "Bearer";

    public JwtTokenResponseDto(String token) {
        this.token = token;
    }
}
