package com.example.nvlv04.model.checker

class checker() {
    private val passwordMinLength = 6
    private val passwordMaxLength = 15
    private var checkerResultBoolean = true
    private var checkerMessage = ""
    fun checkCorrectPassword(inputString: String): Pair<Boolean, String> {
        var (hasSmallCharacter, hasNumber) = listOf(false, false)

        inputString.length
        for (iteratorChar in inputString) {
            when {
                iteratorChar.isLowerCase() -> hasSmallCharacter = true
                iteratorChar.isDigit() -> hasNumber = true
            }
        }
        checkerResultBoolean = hasSmallCharacter && hasNumber && (inputString.length in passwordMinLength..passwordMaxLength)
        when {
            inputString.length < passwordMinLength || inputString.length > passwordMaxLength -> checkerMessage =
                "Password must be $passwordMinLength-$passwordMaxLength characters"
            !(hasSmallCharacter && hasNumber) -> checkerMessage =
                "Password needs a number and a lowercase letter"
        }

        return Pair(checkerResultBoolean, checkerMessage)
    }

}