import time
from rev_ai.speechrec import RevSpeechAPI

def await_transcript(client, id_):
    while client.view_job(id_)['status'] == 'in_progress':
        print('waiting...')
        time.sleep(5)
    return client.get_transcript(id_)

def transcribe():
    api_key = "01mNKNvWtbTclnohMB5fJzSnPiS8anhM43MT1qL8Fd98LqTiqTXJfFaDGwakIIia_mzDmE64ye3xuTiNHzOO4uu8GWW60"
    client = RevSpeechAPI(api_key)                                        

    print(client.get_account())
    result = client.submit_job_local_file("output.wav")

    transcript = await_transcript(client, result['id'])

    # print(transcript['monologues'][0]['elements'][:10])
    result = ""

    for element in transcript['monologues'][0]['elements'][:10]:
        print (element)
        result += element['value']

    return result