import psutil
import time
from langchain_ollama import ChatOllama

def measure_model(model_name, question="Can you check which engine types have a low rating?"):
    """Measure memory and response time for a given model"""
    print(f"\n=== Testing model: {model_name} ===")

    # Medir memoria antes
    time.sleep(3)
    mem_before = psutil.virtual_memory().available / (1024 ** 3)  # GB

    # Crear modelo
    try:
        chat = ChatOllama(model=model_name, temperature=0.7)
    except Exception as e:
        print(f"Could not load {model_name}: {e}")
        return

    # Preguntar
    start_time = time.time()
    try:
        answer = chat.invoke(question)
        end_time = time.time()
    except Exception as e:
        print(f"Query failed: {e}")
        return

    # Medir memoria después
    mem_after = psutil.virtual_memory().available / (1024 ** 3)  # GB

    # Calcular métricas
    duration = end_time - start_time
    mem_used = mem_before - mem_after

    # print(f"Answer: {answer.content[:200]}...")           # No interessa la resposta ara mateix
    print(f"Response time: {duration:.2f} seconds")
    print(f"Approx memory used: {mem_used:.2f} GB")

if __name__ == "__main__":
    # Compara tots els models
    measure_model("llama3.2")
    measure_model("llama3.2:1b")
    measure_model("llama3.1")