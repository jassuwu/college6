import { Injectable } from '@nestjs/common';
import { AddItemsToPlaylistDto } from './dto/add-items-to-playlist.dto';

@Injectable()
export class AppService {

  SPOTIFY_OAUTH_TOKEN = `BQDK6NAloSFghh3ji_muCnORyi3-4mR3TdcHqEcPCu6oadIaAK2Tez2Fpr1CYqTkNgPy_UzV4wzYpMBRUjM3JBg_eVOXjAadK_4E7gUN193CotjD04SuBOnaTOK3GVj12BruItAXlFJ7Rmnpd1M5N6pqAbbdym-NsCiIdrD365tjQ1bnaPbq3QWSMOiN86nNua2jkDxsLJ0b2qS4AydTMSkujuNOpUWsC4gSAzerwNFgEXTLERdcvW3p2myEZCukp9J2pQW6bYJttwfeRloiEVUWVOYQaqsDOK_t-RBpZFTK8knrhbi3G-z8a4DbEviJ7yWNgE28aGJn2g`

  getHello(): string {
    return 'The API is healthy!';
  }

  // E.g. Album ID : 305fd6KSKY40Yjgwvm2ck6
  async getAlbumDetails(albumId: string) {
    try {
      const result = await fetch(`https://api.spotify.com/v1/albums/${albumId}`, {
        method: 'GET',
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${this.SPOTIFY_OAUTH_TOKEN}`
        }
      });
      return result.json();
    } catch (e) {
      console.log(e);
    }
  }

  // E.g. Playlist ID: 62qXsjSDve1tcZUCghDvCi
  // E.g. Body:  { "uris": ["spotify:track:4iV5W9uYEdYUVa79Axb7Rh","spotify:track:1301WleyT98MSxVHPZCA6M", "spotify:episode:512ojhOuo1ktJprKbVcKyQ"] }
  async addItemsToPlaylist(playlistId: string, addItemsToPlaylistDto: AddItemsToPlaylistDto) {
    try {
      const result = await fetch(`https://api.spotify.com/v1/playlists/${playlistId}/tracks`, {
        method: 'POST',
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${this.SPOTIFY_OAUTH_TOKEN}`
        },
        body: JSON.stringify(addItemsToPlaylistDto),
      });
      return result.json();
    } catch (e) {
      console.log(e);
    }
  }

  // E.g. searchTerm: "Queen"
  async search(searchTerm: string) {
    try {
      const result = await fetch(`https://api.spotify.com/v1/search?q=${searchTerm}&type=album,track,artist,playlist`, {
        method: 'GET',
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${this.SPOTIFY_OAUTH_TOKEN}`
        }
      });

      return result.json();
    } catch (e) {
      console.log(e);
    }
  }
}