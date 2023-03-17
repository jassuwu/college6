import { Body, Controller, Get, Param, Post, Query } from '@nestjs/common';
import { AppService } from './app.service';
import { AddItemsToPlaylistDto } from './dto/add-items-to-playlist.dto';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) { }

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }


  @Get('/album/:id')
  // Example call http://localhost:3000/album/305fd6KSKY40Yjgwvm2ck6
  getAlbumDetails(@Param('id') albumId: string) {
    return this.appService.getAlbumDetails(albumId);
  }


  @Post('/addtoplaylist/:id')
  // Example call http://localhost:3000/addtoplaylist/62qXsjSDve1tcZUCghDvCi
  addItemsToPlaylist(@Param('id') playlistId: string, @Body() addItemsToPlaylistDto: AddItemsToPlaylistDto) {
    return this.appService.addItemsToPlaylist(playlistId, addItemsToPlaylistDto);
  }

  @Get('/search')
  // Search with query params
  search(@Query('q') searchTerm: string) {
    return this.appService.search(searchTerm);
  }

  // Example call http://localhost:3000/search/queen

}
