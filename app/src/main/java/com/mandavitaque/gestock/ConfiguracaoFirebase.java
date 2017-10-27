package com.mandavitaque.gestock;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by vinic on 22/10/2017.
 */

public final class ConfiguracaoFirebase {

    public static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;


    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
        public static FirebaseAuth getFirebaseAutenticacao()
        {
            if (autenticacao == null){
                autenticacao = FirebaseAuth.getInstance();

        }
        return autenticacao;


    }
}
