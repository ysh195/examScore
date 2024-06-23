1. 5주차 연습.
2. ArrayList와 stream을 주로 사용해서 만든다는 주목적은 달성함.
3. 이번에는 구성과 연결에 좀 더 신경 써서 코딩하고, 각 객체들을 연결하는 과정이 전보다 매끄러웠음
4. 하지만 결과 출력하는 것을 그냥 콘솔 메시지로 출력하는 것에서 그래프로 출력하는 것으로 수정하는 과정에서 ArrayList와 stream의 비중이 확 줄어들었음
5. 이번에 만들면서 왜 ui를 기준으로 코드를 설계해야 하는지 깨달았음. 결국 가장 많은 데이터 입력과 오류 보정 등이 필요한 곳은 유저가 사용하는 곳이고, 그곳을 어떻게 처리할 지가 핵심임
   - 이전에는 ui가 빈약하고, 그냥 내부적으로 처리하는 게 대부분이어서 데이터 저장을 기준으로 했지만, 이번에 ui를 더 추가해보니 유저가 입력할 데이터가 먼저고, 저장소는 그 다음.
