# KDC

Duas entidades (Alice e Bob) se conversam através de criptografia simétrica:

1. Bob e o KDC devem compartilhar uma chave mestre: 𝐾𝑏𝑜𝑏; 
2. Alice e o KDC devem compartilhar uma chave mestre: 𝐾𝑎𝑙𝑖𝑐𝑒; 
3. Bob e Alice devem conversar através de uma chave de sessão (𝐾𝑠𝑒𝑠𝑠ã𝑜); 
4. A chave de sessão deve ser obtida através de uma comunicação criptografada com o KDC, utilizando a chave mestre; 
5. Quando ambas entidades possuírem a chave de sessão, Bob gera um nonce e encaminha para Alice, cifrando na 𝐾𝑠𝑒𝑠𝑠ã𝑜; 
6. Alice responde Bob executando uma função sobre o nonce recebido, cifrando na 𝐾𝑠𝑒𝑠𝑠ã𝑜; 
7. Bob compara o valor recebido com o valor de nonce enviado realizando a função; 
