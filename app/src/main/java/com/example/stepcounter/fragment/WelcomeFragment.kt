package com.example.stepcounter.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stepcounter.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LoginFragment : Fragment() {

    private var _vb: FragmentWelcomeBinding? = null
    private val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _vb = FragmentWelcomeBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vb.btnLogin.setOnClickListener {
            val email = vb.etEmail.text?.toString().orEmpty()
            val pass  = vb.etPassword.text?.toString().orEmpty()

            var ok = true
            if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                vb.tilEmail.error = "Geçerli e-posta gir"
                ok = false
            } else vb.tilEmail.error = null

            if (pass.length < 6) {
                vb.tilPassword.error = "En az 6 karakter"
                ok = false
            } else vb.tilPassword.error = null

            if (ok) {
                val remember = if (vb.cbRemember.isChecked) " (beni hatırla açık)" else ""
                Toast.makeText(requireContext(), "Giriş başarılı$remember", Toast.LENGTH_SHORT).show()
                // TODO: NavController ile ana sayfaya geç
                // findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        vb.tvForgot.setOnClickListener {
            Toast.makeText(requireContext(), "Şifre sıfırlama akışını ekleyin", Toast.LENGTH_SHORT).show()
            // findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        vb.tvRegister.setOnClickListener {
            Toast.makeText(requireContext(), "Kayıt ekranına gidin", Toast.LENGTH_SHORT).show()
            // findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}
