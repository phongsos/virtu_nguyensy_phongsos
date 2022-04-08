# 5I_SY3 : Projet de virtualisation
**2021/2022 - ESIEE Paris**  

Sylvie NGUYEN - [sylvie.nguyen@edu.esiee.fr](mailto:sylvie.nguyen@edu.esiee.fr)  
Saysana PHONGSOUVANH - [saysana.phongsouvanh@edu.esiee.fr](mailto:saysana.phongsouvanh@edu.esiee.fr)

# Environnement de développement
- **Minikube:** v1.25.1
- **Kubernetes (kubectl):** v1.22.5 (client) & v.1.23.1 (server)
- **Angular CLI:** v13.2.6
- **Node:** v16.14.0
- **Package Manager:** npm v8.3.0
- **Docker:** v20.10.12

# Exécution
Il est nécessaire de lancer Minikube avec la commande suivante afin d'ouvrir les ports de nos services. 
Il s'agit d'un contournement d'un bug rencontré avec notre version de Minikube sous Windows 10 (https://stackoverflow.com/questions/71384252/cannot-access-deployed-services-when-minikube-cluster-is-installed-in-wsl2)
```bash
minikube delete # Pour supprimer le profil minikube existant
minikube start --ports=127.0.0.1:31000:31000,127.0.0.1:31001:31001,127.0.0.1:31002:31002
```

Ensuite, à la racine du projet, il faut appliquer notre yaml :
```bash
kubectl apply -f project-app.yml
```

Le front-end de l'application est alors accessible depuis l'adresse `http://localhost:31000` 
tandis que deux microservices sont accessibles via les adresses `http://localhost:31001` et `http://localhost:31002`.

# Qwiklabs
![Qwiklabs Sylvie Nguyen](img/qwiklabs_nguyensy.png)

![Qwiklabs Saysana Phongsouvanh](img/qwiklabs_phongsos.png)