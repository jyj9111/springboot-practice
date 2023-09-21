package com.example.vaildation.dto;

import com.example.vaildation.constraints.annotations.Blacklist;
import com.example.vaildation.constraints.annotations.EmailWhitelist;
import com.example.vaildation.constraints.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;

    @NotBlank // 비어있으면 안된다.
//    @Size(min = 8, message = "최소 8글자 여야 합니다.")
    @Blacklist(blacklist = {"alex", "beroca"})
    private String username;
    @EmailWhitelist  // 형식이 이메일이어야 한다.
    // 이메일이 지정된 도메인(gmail.com 등) 이도록
    // 검증하는 어노테이션을 만들어 봅시다.
    private String email;
    @NotNull
    @Phone010   // 010 으로 시작하는 번호 형식인지
    private String phone;

    @Min(
            value = 14,
            message = "14세 미만은 부모님의 동의가 필요합니다."
    )    // 최솟값
    private Integer age;

    @Future(message = "미래의 시간까지 유효해야 합니다.") // 미래의 시간만 가능
    private LocalDate validUntil;

    @NotNull // notNullString 이 널이 아닌지만 검증
    private String notNullString;
    @NotEmpty // notEmptyString 의 길이가 0이 아닌지만 검증
    private String notEmptyString;
    @NotBlank // notBlankString 이 공백 문자로만 이루어지지 않았는지 검증
    private String notBlankString;
}

/*
{
    "username": "jeeho.dev",
    "email": "jeeho.dev@gamil.com",
    "phone": "010-1234-1234"
}
*/