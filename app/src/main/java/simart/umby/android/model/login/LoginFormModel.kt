package simart.umby.android.model.login

data class LoginFormModel(
    var username: String = "",
    var password: String = "",
    var checkbox: Boolean = false,
) {
    fun enableLoginButton(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    override fun toString(): String {
        return "Username $username, Password: $password"
    }
}
